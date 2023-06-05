package com.example.demo.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PlaylistWithSongsDto;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.entities.SongXPlaylist;
import com.example.demo.repository.SongXPlaylistRepository;
import com.example.demo.service.SongXPlaylistService;
@Service
public class SongXPlaylistImpl implements SongXPlaylistService{

	@Autowired
	private SongXPlaylistRepository songXPlayListRepository;

	@Override
	public List<SongXPlaylist> getAll() {
		return songXPlayListRepository.findAll();
		
	}

	@Override
	public void insertSongsIntoPlaylist(SongXPlaylist newSong) {
		songXPlayListRepository.save(newSong);
	}

	@Override
	public PlaylistWithSongsDto findSongsByPlaylist(Playlist playlist) {
		List<SongXPlaylist> songs = songXPlayListRepository.findSongsByPlaylist(playlist);
		List<Song> songsToInsert = new ArrayList();
		Integer duration = 0;
        for(SongXPlaylist songXPlaylist : songs) {
        	Song song = songXPlaylist.getSong();
        	duration += song.getDuration();
        	songsToInsert.add(song);
        	
        }
        PlaylistWithSongsDto playlistwithsongs = new PlaylistWithSongsDto();
        playlistwithsongs.setSongs(songsToInsert);
        playlistwithsongs.setPlaylist(playlist);
        playlistwithsongs.setDuration(duration);
        
		return playlistwithsongs;
	}

	 @Override
	    public boolean isSongInPlaylist(Song songCode, Playlist playlistCode) {
	        List<SongXPlaylist> songXPlaylists = songXPlayListRepository.findBySongAndPlaylist(songCode, playlistCode);

	        return !songXPlaylists.isEmpty();
	    }

}
