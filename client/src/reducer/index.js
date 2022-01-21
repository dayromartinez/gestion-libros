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
            return { ...state, catalogo: action.payload, loading: false, hasErrors: false };

        case actions.LOADED_FAILURE:
            return { ...state, loading: false, hasErrors: true }

        default:
            return state;
    }
}