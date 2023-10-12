import { TicketEndpoints } from "@/enums/APIEnum";
import axios from "axios";

export const getAllTickets = async (token: string) => {
  const res = axios
    .get(TicketEndpoints.GetAllTickets, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data.data;
      } else {
        return [];
      }
    });
  return res;
};

export const getTicketById = async (token: string, id: string | string[]) => {
  const res = axios
    .get(TicketEndpoints.GetTicketById + id, {
      headers: {
        token: token,
      },
    })
    .then((res) => {
      if (res.data.success) {
        return res.data.data;
      }
    });
  return res;
};
