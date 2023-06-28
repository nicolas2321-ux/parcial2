import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import { getSongs } from '../services/playlist';
export default function ModalResultado(props){
    const token = localStorage.getItem('item')
    const [canciones, setCanciones] = useState([])
    useEffect(() => {

       async function searchSong(){
        console.log(props.title)
        const object = {
            token: token,
            cancion: props.title
        }
        const res = await getSongs(object)
        const songs = (await res.json())
        setCanciones(songs)
       }
       searchSong()
    
      }, [props.show]);
    return(
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
            {canciones.map((item) => (
                <tr>
                  <td>{item.title}</td>
                  <td>{item.duration}</td>
                </tr>
              ))}
            </tbody>
          </table>
       
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={props.onHide}>
            Close
          </Button>
          <Button variant="primary" >
            Buscar
          </Button>
        </Modal.Footer>
      </Modal>
    )
}