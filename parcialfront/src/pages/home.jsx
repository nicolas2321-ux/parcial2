import { useState } from 'react'
import '../css/design.css'
import ModalBuscarCancion from '../components/modalBuscarCancion'
import ModalCrearPlaylist from '../components/modaCrearPlaylist'
import ModalBuscarPlaylist from '../components/modalBuscarPlaylist'

export default function Home(){
    const [showBuscarCancion, setShowBuscar] = useState(false)
    const [showCrearPlaylist, setShowCrearPlaylist] =useState(false)
    const [showBuscarPlaylist, setShowBuscarPlaylist] = useState(false)
    return(
        <>
        <ModalBuscarCancion
        show={showBuscarCancion}
        onHide={()=> {setShowBuscar(false)}}
        />
        <ModalCrearPlaylist
        show={showCrearPlaylist}
        onHide={() => {setShowCrearPlaylist(false)}}
        />
        <ModalBuscarPlaylist
        show={showBuscarPlaylist}
        onHide={() => {setShowBuscarPlaylist(false)}}/>
        <div className="container">
        <div className="row justify-content-center align-items-center min-vh-100">
          <div className="col-sm-6 purple-div text-center">
            <div className="row mb-3">
              <div className="col">
                <button className="btn btn-primary" onClick={() => {setShowBuscar(true)}}>Buscar cancion</button>
              </div>
            </div>
            <div className="row mb-3">
              <div className="col">
                <button className="btn btn-primary" onClick={() => {setShowCrearPlaylist(true)}}>Crear una playlist</button>
              </div>
            </div>
            <div className="row mb-3">
              <div className="col">
                <button className="btn btn-primary" onClick={() => {setShowBuscarPlaylist(true)}}>Buscar una playlist</button>
              </div>
            </div>
            
           
          </div>
        </div>
      </div>
      </>
    )
}