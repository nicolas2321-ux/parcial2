import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import { getSongs } from '../services/playlist';
import Pagination from 'react-bootstrap/Pagination';
export default function ModalResultado(props){
    const token = localStorage.getItem('item')
    const [canciones, setCanciones] = useState([])
    const [page, setPage] = useState(0)
    const [title, setTitle] = useState('')
    useEffect(() => {
      setTitle(props.title)
       async function searchSong(){
        console.log(props.title)
        const object = {
            token: token,
            cancion: title,
            page: page

        }
        const res = await getSongs(object)
        const songs = (await res.json())
        setCanciones(songs.content)
       }
       searchSong()
    
      }, [props.show, page]);

      const handlePrev = () => {
        if(page !== 0){
          setPage(page-1)
        }
      }
      const hanldeNext = () => {
      
          setPage(page+1)
        
      }
      
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
    )
}