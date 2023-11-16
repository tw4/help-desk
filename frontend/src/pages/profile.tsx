import MainLayout from "@/components/layout/MainLayout";
import { useAppDispatch, useAppSelector } from "@/hook/Redux";
import { useAuth } from "@/hook/Auth";
import { useRouter } from "next/router";
import { User } from "@/types/userType";
import { useEffect } from "react";
import { getUser } from "@/api/user";
import { addUser } from "@/store/features/user/userSlice";

const Profile = () => {
  // auth
  useAuth();

  // router
  const router = useRouter();

  // redux state
  const user: User = useAppSelector((state) => state.user).value as User;

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
      <div className="container">
        <h1>Profile</h1>
      </div>
    </MainLayout>
  );
};

export default Profile;
