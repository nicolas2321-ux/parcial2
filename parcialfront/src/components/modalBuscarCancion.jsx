import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import ModalResultado from './modalResultadoCancion';
import { useEffect } from 'react';
export default function  ModalBuscarCancion(props){
    const [nombreCancion, setNombreCancion] = useState('')
    const [showCanciones, setMostarCanciones] = useState(false)
    useEffect(() => {
      setNombreCancion('')
    }, [props.show])
    
    const handleBuscar = () => {
        //props.onHide()
        setMostarCanciones(true)
    }
    return(
        <>
        <ModalResultado
        show={showCanciones}
        onHide= {() => {setMostarCanciones(false)}}
        title={nombreCancion}
        />
        <Modal show={props.show} onHide={props.onHide}>
        <Modal.Header closeButton>
          <Modal.Title>Buscar cancion</Modal.Title>
        </Modal.Header>
        <Modal.Body>Recuerda que puedes dejar el espacio en blanco para buscar todas las canciones
        <Form.Control type="text" placeholder="Nombre cancion" onChange={(e) => {setNombreCancion(e.target.value)}} />
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