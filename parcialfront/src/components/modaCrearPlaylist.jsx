import { useState } from "react"
import Swal from 'sweetalert2'
import { inserPlaylist } from "../services/playlist"
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';

export default function ModalCrearPlaylist(props){
    const [title, setTitle] = useState('')
    const [descripcion, setDescripcion] = useState('')
    const token = localStorage.getItem('item')

    const handleBuscar = async() => {
        if(title === '' || descripcion === ''){
            Swal.fire(
                'Se detectaron campos vacios',
                '',
                'error'
              )
        }else{
            const object = {
                title: title,
                descripcion: descripcion,
                token: token
            }
            const res = await inserPlaylist(object)
            const playlist = (await res.status)
            if(playlist === 200){
                Swal.fire(
                    'Se creo la playlist',
                    '',
                    'success'
                  )
                props.onHide()
            }else{
                Swal.fire(
                    'Ocurrio un error inesperado',
                    '',
                    'error'
                  )
            }
        }
    }
    return(
        <>
        <Modal show={props.show} onHide={props.onHide}>
        <Modal.Header closeButton>
          <Modal.Title>Buscar cancion</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <Form.Control type="text" placeholder="Titulo de la playlist" onChange={(e) => {setTitle(e.target.value)}} />
        <br />
        <Form.Control type="text" placeholder="Descripcion" onChange={(e) => {setDescripcion(e.target.value)}} />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={props.onHide}>
            Close
          </Button>
          <Button variant="primary" onClick={handleBuscar}>
            Buscar
          </Button>
        </Modal.Footer>
      </Modal>
        </>
    )
}