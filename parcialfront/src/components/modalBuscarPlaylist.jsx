import { useState, useEffect } from "react"
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import { ModalResultadoPlaylist } from "./modalResultadoPlaylist";

export default function ModalBuscarPlaylist(props){
    const [nombrePlaylist, setNombrePlaylist] =useState('')
    const [showResultado, setShowResultado] = useState(false)
    const token = localStorage.getItem('item')

    useEffect(() => {
      return () => {
        setNombrePlaylist('')
      };
    }, [props.show])
    
    const handleBuscar = async () => {
        setShowResultado(true)
        //props.onHide()
    }
    return (
        <>
        <ModalResultadoPlaylist
        show={showResultado}
        onHide = {() => {setShowResultado(false)}}
        title={nombrePlaylist}/>
          <Modal show={props.show} onHide={props.onHide}>
        <Modal.Header closeButton>
          <Modal.Title>Buscar playlist</Modal.Title>
        </Modal.Header>
        <Modal.Body>Recuerda que puedes dejar el espacio en blanco para buscar todas tus playlist
        <Form.Control type="text" placeholder="Nombre de la playlist" onChange={(e) => {setNombrePlaylist(e.target.value)}} />
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