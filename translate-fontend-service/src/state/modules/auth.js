import axios from 'axios';

const state = {
  authToken: getSavedState('auth.token'),
};

const getters = {
  // return function to avoid cache
  isLogged: state => () => {
    if (state.authToken) {
      return isValid(state.authToken);
    }
    return false;
  },
  getUsername(state) {
    if (isValid(state.authToken)) {
      const parsedToken = getParsedToken(state.authToken);
      return parsedToken.sub;
    }
    return '';
  },
};

const mutations = {
  SET_AUTH_TOKEN(state, token) {
    state.authToken = token;
    saveState('auth.token', token);
    setDefaultAuthHeaders(token);
  },
};

const actions = {
  login({ commit, getters }, { username, password }) {
    if (getters.isLogged()) return null;

    const auth = new FormData();
    auth.append('username', username);
    auth.append('password', password);

    axios.post('/login', auth)
      .then((response) => {
        const token = response.data.Authorization;
        commit('SET_AUTH_TOKEN', token);
      })
      .catch(e => e);
  },

  logout({ commit }) {
    commit('SET_AUTH_TOKEN', null);
  },
};

// ===
// Helpers
// ===
function isValid(token) {
  if (!token || token.split('.').length < 3) {
    return false;
  }
  const data = getParsedToken(token);
  const exp = new Date(data.exp * 1000);
  const now = new Date();
  return now < exp;
}

function getSavedState(key) {
  return window.localStorage.getItem(key);
}

function saveState(key, state) {
  window.localStorage.setItem(key, state);  //JSON.stringify(state));
}

function setDefaultAuthHeaders(state) {
  axios.defaults.headers.common.Authorization = state ? state : '';
}

function getParsedToken(token) {
  return JSON.parse(atob(token.split('.')[1])); //JSON.parse(atob(token.split('.')[1]));
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
