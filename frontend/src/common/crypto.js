var CryptoJS = require("crypto-js");

export const Crypto = {
  /**
   * salt=476B38856A7A1311
   * key=2E1655F7CCDF670AB23F56D0820E4918
   * iv =155DB2C9362C15A7AF3F461383B8F108
   */
  cryptoAesEncrypt(value) {
    var aesKey = CryptoJS.enc.Utf8.parse("2E1655F7CCDF670AB23F56D0820E4918");
    var iv = CryptoJS.enc.Utf8.parse("155DB2C9362C15A7AF3F461383B8F108");
    var encrypted = CryptoJS.AES.encrypt(value, aesKey, { iv: iv }).toString();
    return encrypted;
  },
  cryptoAesDecrypt(value) {
    var aesKey = CryptoJS.enc.Utf8.parse("2E1655F7CCDF670AB23F56D0820E4918");
    var iv = CryptoJS.enc.Utf8.parse("155DB2C9362C15A7AF3F461383B8F108");
    var encrypted = CryptoJS.AES.decrypt(value, aesKey, { iv: iv }).toString();
    return encrypted;
  }
};

export default Crypto;
