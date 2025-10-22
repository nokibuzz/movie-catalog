import { keys } from "../common/action-keys";

const initialState = {
  currentUser: {},
};

export default function reducer(state = initialState, action) {
  switch (action.type) {
    case keys.LOGIN_USER:
      return { ...state, currentUser: action.payload };
    default:
      return state;
  }
}
