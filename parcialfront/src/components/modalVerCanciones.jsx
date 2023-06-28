import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import { useEffect, useState } from 'react';
import { songsInPlaylist } from '../services/playlist';
export default function ModalVerCancionesPlaylist(props){
    const token = localStorage.getItem('item')
    const [songs, setSongs] = useState([])
    const [totalDuration, setTotalDuration] = useState(0)
    useEffect(() => {
        const getSongs  = async () =>{
            const object = {
                token: token,
                idPlaylist: props.idPlaylist
            }
            if(props.idPlaylist !== undefined){
            const res = await songsInPlaylist(object)
            const resultado = await res.json()
            setTotalDuration(resultado.duration)
            setSongs(resultado.songs)
            console.log(resultado)
            }
        }
        getSongs()
    },[props.show])
    return(
        <>
         <Modal show={props.show} onHide={props.onHide}>
        <Modal.Header closeButton>
          <Modal.Title>Resultado</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <table className="table">
            <thead>
              <tr>
                <th>Title</th>
                <th>Duracion</th>
                
              </tr>
            </thead>
            <tbody>
            {songs.map((item) => (
                <tr>
                  <td>{item.title}</td>
                  <td>{item.duration}</td>
                </tr>
              ))}
            </tbody>
          </table>
                <p>Duracion total de la playlist: {totalDuration} minutos</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={props.onHide}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
        </>
    )
}