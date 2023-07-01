import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import { useState, useEffect } from "react"
import { getSongsforSelect } from '../services/playlist';
import Swal from 'sweetalert2'
import { setSongs } from '../services/playlist';
export default function ModalInsertarCancion(props){
    const token = localStorage.getItem('item')
    const [canciones, setCanciones] = useState([])
    const [cancionId, setCancionId] = useState('')
    const [page, setPage] = useState(0)
   
    useEffect(() => {
        const searchSong = async() =>{
            const object = {
                token: token,
                cancion: '',
                page:page
            }
            const res = await getSongsforSelect(object)
            const songs = (await res.json())
            console.log(songs)
            setCanciones(songs)
        } 
        searchSong()
    }, [props.show])

    const handleBuscar = async()=>{
        if(cancionId === 0){
            Swal.fire(
                'Elige una cancion',
                '',
                'error'
              )
        }else{
            // Obtener la fecha actual
            var fecha = new Date();

            // Obtener los componentes de la fecha
            var year = fecha.getFullYear().toString().slice();
            console.log(year) // Obtener los últimos dos dígitos del año
            var month = ("0" + (fecha.getMonth() + 1)).slice(-2); // Los meses van de 0 a 11, por eso se suma 1
            var day = ("0" + fecha.getDate()).slice(-2);

            // Formatear la fecha en el formato YY/MM/DD
            var fechaFormateada = year + "-" + month + "-" + day;
            const object = {
                idPlaylist : props.idPlaylist,
                token: token,
                code: cancionId,
                fecha: fechaFormateada
            }
            const res = await setSongs(object)

            const status = await res
            if(status.status === 200){
                Swal.fire(
                    'Cancion agregada correctamente',
                    '',
                    'success'
                  )
                  props.onHide()
            }else if(status.status === 400){
                Swal.fire(
                    'La cancion ya esta en la playlist',
                    '',
                    'error'
                  )
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
          <Modal.Title>Insertar cancion</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <Form.Select aria-label="Default select example" onChange={(e) => {setCancionId(e.target.value)}}>
            <option value={0}>Selecciona una cancion</option>
            {canciones.map((item) => (
            <option value={item.code}>{item.title}</option>
            
            ))}
        </Form.Select>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={props.onHide}>
            Close
          </Button>
          <Button variant="primary" onClick={handleBuscar}>
            Insertar
          </Button>
        </Modal.Footer>
      </Modal>
        </>
    )
}