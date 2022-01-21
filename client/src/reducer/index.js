import * as actions from '../actions/index.js';

export const initialState = {

    loading: true,
    hasErrors: false,
    catalogo: [],
    search: "",

}

export default function rootReducer(state = initialState, action) {

    switch (action.type) {

        case actions.LOADING:
            return { ...state, loading: true };

        case actions.LOADED_SUCCESS:

            var data = action.payload[0].libro;
            var nombres = Object.keys(data);
            var libros = [];

            for (let i = 0; i < nombres.length; i++) {
                for (const libro in data) {
                    if(libro === nombres[i]){
                        libros.push(data[libro]);
                    }
                }
            }

            return { ...state, catalogo: libros, loading: false, hasErrors: false };

        case actions.LOADED_FAILURE:
            return { ...state, loading: false, hasErrors: true }

        default:
            return state;
    }
}