import { Title } from "./titleType";

export type User = {
  id: number;
  name: string;
  surname: string;
  email: string;
  password: string;
  role: string;
  title: Title;
};
