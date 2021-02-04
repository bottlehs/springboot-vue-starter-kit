import Jwt from "./jwt";

let isRefreshing = false;
let failedQueue = [];

const processQueue = (error, accessToken = null) => {
  failedQueue.forEach(prom => {
    if (error) {
      prom.reject(error);
    } else {
      prom.resolve(accessToken);
    }
  });

  failedQueue = [];
};

const interceptor = axiosInstance => error => {
  const _axios = axiosInstance;
  const originalRequest = error.config;

  if (error.response) {
    console.log(originalRequest.url);
    if (originalRequest.url.indexOf("/api/oauth/login") == 0) {
      console.log("/oauth/login");
      Promise.reject(error);
    } else if (error.response.status === 401 && !originalRequest._retry) {
      if (isRefreshing) {
        return new Promise(function(resolve, reject) {
          failedQueue.push({ resolve, reject });
        })
          .then(accessToken => {
            originalRequest.headers["Authorization"] = "Bearer " + accessToken;
            return _axios.request(originalRequest);
          })
          .catch(err => {
            return Promise.reject(err);
          });
      }

      originalRequest._retry = true;
      isRefreshing = true;

      const refreshToken = Jwt.getRefreshToken();
      return new Promise((resolve, reject) => {
        // Refresh Token 으로 AccessToken 재발급 요청
        _axios
          .post("/api/oauth/refresh", { certify: { refreshToken } })
          .then(({ data }) => {
            console.log("interceptor_ refresh--------");
            console.log(data);
            Jwt.saveToken(data.accessToken);
            _axios.defaults.headers.common["Authorization"] =
              "Bearer " + data.accessToken;
            originalRequest.headers["Authorization"] =
              "Bearer " + data.accessToken;
            processQueue(null, data.accessToken);
            resolve(_axios(originalRequest));
          })
          .catch(err => {
            console.log("interceptor_ CATCH");
            processQueue(err, null);
            reject(err);
          })
          .finally(() => {
            isRefreshing = false;
          });
      });
    } else if (error.response.status == 400) {
      if (originalRequest.url.indexOf("/api/oauth/refresh") == 0) {
        // 리프레시토큰 요청후 실패 > 로그아웃
      } else {
        if (error.response.data.msg) {
          // 에러 메시지
        }
      }
    }
  }

  return Promise.reject(error);
};

export default interceptor;
