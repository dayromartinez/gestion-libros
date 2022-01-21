import img from "../imagenes/sofkau.png"

function Footer() {

    return (
        <nav class="navbar navbar-dar bg-dark">
            <div class="container-fluid">
                <h3 class="navbar-brand" href="#" id="titulo_footer">
                <img src={img} alt="logo página" width="100" height="50" class="d-inline-block" id="img_navbar"/>
                    © SofkaU 2022. Aplicación web optimizada por Dayro Martínez. Todos los derechos reservados.
                </h3>
            </div>
        </nav>
    )
}

export default Footer;