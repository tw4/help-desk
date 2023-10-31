import { Config } from "@/enums/APIEnum";
import axios from "axios";

export const getDefaultConfigList = async (token: string) => {
  const res = await axios
    .get(Config.GetConfigList, {
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

export const getDefaultConfig = async (token: string) => {
  const res = await axios
    .get(Config.GetDefaultConfig, {
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

export const updateDefaultConfig = async (token: string, id: number) => {
  const res = await axios
    .patch(
      Config.UpdateDefaultConfig + id,
      {},
      {
        headers: {
          token: token,
        },
      }
    )
    .then((res) => {
      if (res.data.success) {
        return res.data;
      } else {
        return null;
      }
    });
  return res;
};

export const addConfig = async (
  token: string,
  openTicketStatusId: number,
  closeTicketStatusId: number
) => {
  const res = await axios
    .post(
      Config.AddConfig,
      {
        openTicketStatusId: openTicketStatusId,
        closeTicketStatusId: closeTicketStatusId,
      },
      {
        headers: {
          token: token,
        },
      }
    )
    .then((res) => {
      if (res.data.success) {
        return res.data;
      } else {
        return null;
      }
    });
  return res;
};

export const deleteConfig = async (token: string, id: number) => {
  const res = await axios
    .delete(Config.DeleteConfig + id, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data;
      } else {
        return null;
      }
    });
  return res;
};
