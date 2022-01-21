import img from "../imagenes/libro8.png"

function NavBar() {

    return (
        <nav class="navbar navbar-dar bg-dark">
            <div class="container-fluid">
                <h3 class="navbar-brand" href="#" id="titulo_navbar">
                <img src={img} alt="logo página" width="80" height="80" class="d-inline-block" id="img_navbar"/>
                    La lectura en los tiempos del Training
                </h3>
            </div>
        </nav>
    )
}

export default NavBar;