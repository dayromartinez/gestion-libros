import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { fetchCatalogo } from '../actions/index.js';


function Catalogo() {

    const dispatch = useDispatch();
    const catalogo = useSelector((state) => state.catalogo);
    const loading = useSelector((state) => state.loading);
    console.log(catalogo);

    useEffect(() => {
        dispatch(fetchCatalogo());
    }, [dispatch])

    return (
        <div>
            <h2>¿Qué quieres leer hoy?</h2>
        </div>
    )
}

export default Catalogo;