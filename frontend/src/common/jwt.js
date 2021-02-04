const ID_TOKEN_KEY = "id_access_token";
const ID_REFRESH_TOKEN_KEY = "id_refresh_token";

export const getAccessToken = () => {
  return window.localStorage.getItem(ID_TOKEN_KEY);
};

export const saveAccessToken = token => {
  window.localStorage.setItem(ID_TOKEN_KEY, token);
};

export const destroyAccessToken = () => {
  window.localStorage.removeItem(ID_TOKEN_KEY);
};

export const getRefreshToken = () => {
  return window.localStorage.getItem(ID_REFRESH_TOKEN_KEY);
};

export const saveRefreshToken = token => {
  window.localStorage.setItem(ID_REFRESH_TOKEN_KEY, token);
};

export const destroyRefreshToken = () => {
  window.localStorage.removeItem(ID_REFRESH_TOKEN_KEY);
};

export default {
  getAccessToken,
  saveAccessToken,
  destroyAccessToken,
  getRefreshToken,
  saveRefreshToken,
  destroyRefreshToken
};
