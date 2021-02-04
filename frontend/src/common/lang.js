const ID_LANG_KEY = "id_lang";

export const getLang = () => {
  return window.localStorage.getItem(ID_LANG_KEY);
};

export const saveLang = lang => {
  window.localStorage.setItem(ID_LANG_KEY, lang);
};

export const destroyLang = () => {
  window.localStorage.removeItem(ID_LANG_KEY);
};

export default {
  getLang,
  saveLang,
  destroyLang
};
