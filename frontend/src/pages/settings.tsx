import { getUser } from "@/api/user";
import MainLayout from "@/components/layout/MainLayout";
import { Role } from "@/enums/Role";
import { useAuth } from "@/hook/Auth";
import { useAppDispatch, useAppSelector } from "@/hook/Redux";
import { addUser } from "@/store/features/user/userSlice";
import { User } from "@/types/userType";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { z } from "zod";

// basic info form schema
const basicInfoFormSchema = z.object({
  firstName: z.string().min(2, "First name must be at least 2 characters"),
  lastName: z.string().min(2, "Last name must be at least 2 characters"),
  email: z.string().email("Invalid email address"),
  role: z.string(),
});

// password form schema
const passwordFormSchema = z.object({
  password: z.string().min(6, "Password must be at least 6 characters"),
  confirmPassword: z.string().min(6, "Password must be at least 6 characters"),
});

const Settings = () => {
  // auth
  useAuth();

  // router
  const router = useRouter();

  // redux state
  const user: User = useAppSelector((state) => state.user.value as User);

  // redux dispatch
  const dispatch = useAppDispatch();

  useEffect(() => {
    if (!user) {
      getUser(localStorage.getItem("token") || "").then((res) => {
        if (res) {
          dispatch(addUser(res));
        } else {
          router.push("/");
        }
      });
    }
  }, []);

  return (
    <MainLayout>
      <h1 className="text-3xl font-bold mt-2">My Account</h1>
    </MainLayout>
  );
};

export default Settings;
