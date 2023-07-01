import { useEffect, useState } from "react"
import { buscarPlaylist } from "../services/playlist"
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import ModalInsertarCancion from "./modalInsertarCancion";
import ModalVerCancionesPlaylist from "./modalVerCanciones";
import Pagination from 'react-bootstrap/Pagination';

export function ModalResultadoPlaylist(props){
    const [playlist, setPlaylist] = useState([])
    const token = localStorage.getItem('item')
    const [idCancion, setIdCancion] = useState()
    const [showInsertar, setShowInsertar] = useState(false)
    const [showCanciones, setShowCanciones] = useState(false)
    const [page, setPage] = useState(0)
    useEffect(() => {
        const buscar = async() => {
            const object = {
                token: token,
                title: props.title,
                page: page
            }
            const res = await buscarPlaylist(object)

            const play = await res.json()
           console.log(play)
            setPlaylist(play.content)
         }
         buscar()

    }, [props.show, page])

    const handleAgregarCancion = (code) => {
        setIdCancion(code)
        setShowInsertar(true)
    }
    const handleVerCancion = (code) => {
        setIdCancion(code)
        setShowCanciones(true)
    }
    const handlePrev = () => {
      if(page !== 0){
        setPage(page-1)
      }
    }
    const hanldeNext = () => {
    
        setPage(page+1)
      
    }
    return(
        <>
        <ModalInsertarCancion
        show={showInsertar}
        onHide = {() => {setShowInsertar(false)}}
        idPlaylist = {idCancion}
        />
        <ModalVerCancionesPlaylist
        show={showCanciones}
        onHide={() => {setShowCanciones(false)}}
        idPlaylist = {idCancion}
        />
         <Modal show={props.show} onHide={props.onHide}>
        <Modal.Header closeButton>
          <Modal.Title>Resultado</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <table className="table">
            <thead>
              <tr>
                <th>Title</th>
                <th>Descripcion</th>
                <th>Acciones</th>
                
              </tr>
            </thead>
            <tbody>
            {playlist.map((item) => (
                <tr>
                  <td>{item.title}</td>
                  <td>{item.description}</td>
                  <td>
                    <Button variant="primary" onClick={()=> {handleAgregarCancion(item.code)}}>Agregar cancion</Button>
                    <Button variant="success" onClick={()=> {handleVerCancion(item.code)}} style={{marginTop:"10px"}}>Ver canciones</Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          <div style={{ margin: 'auto', display: 'table' }}>
        <Pagination>
          <Pagination.Prev onClick={handlePrev}/>
          <Button variant='primary' disabled>{page+1}</Button>
          <Pagination.Next onClick={hanldeNext}/>
        </Pagination>
        </div>

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