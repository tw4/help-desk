import { UserEndpoints } from "@/enums/APIEnum";
import axios from "axios";

export const getUser = (token: string) => {
  const res = axios
    .get(UserEndpoints.GetMe, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data.data;
      } else {
        return null;
      }
    });
  return res;
};

export const getAllSupporters = (token: string) => {
  const res = axios
    .get(UserEndpoints.getAllSupporters, {
      headers: { token: localStorage.getItem("token") },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data.data;
      } else {
        return null;
      }
    });
  return res;
};

export const UpdateUserBasicInfo = (
  token: string,
  data: any,
  userID: number
) => {
  const res = axios
    .put(UserEndpoints.UpdateUserBasicInfo + userID, data, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return true;
      } else {
        return null;
      }
    });
  return res;
};

export const UpdateUserPassword = (
  token: string,
  data: string,
  userID: number
) => {
  const res = axios
    .patch(
      UserEndpoints.UpdateUserPassword.replace("{id}", userID.toString()),
      {
        password: data,
      },
      {
        headers: {
          token: token,
        },
      }
    )
    .then((res) => {
      if (res.data.success) {
        return true;
      } else {
        return null;
      }
    });
  return res;
};
