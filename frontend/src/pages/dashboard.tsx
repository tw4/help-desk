import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { getUser } from "@/api/user";
import { addUser } from "@/store/features/user/userSlice";
import MainLayout from "@/components/layout/MainLayout";
import { useAuth } from "@/hook/Auth";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

const Dashboard = () => {
  // check auth
  useAuth();
  // Get the `user` state from Redux
  const dispatch = useDispatch();

  // Fetch user data when the component mounts
  useEffect(() => {
    // Assuming getUser returns a Promise<User>
    getUser(localStorage.getItem("token") || "")
      .then((res) => {
        // Dispatch an action to set the `user` state to the fetched user data.
        dispatch(addUser(res));
      })
      .catch((error) => {
        // Handle errors here
        console.error("Error fetching user data", error);
      });
  }, [dispatch]); // Include dispatch in the dependency array

  return (
    <MainLayout>
      <div className="p-2">
        <div className="flex flex-row justify-between mt-2">
          <div className="flex flex-row justify-between space-x-3">
            <Input
              className="w-56"
              type="text"
              placeholder="search ticket title"
            />
            <Button>Search</Button>
          </div>
          <Button>New Ticket</Button>
        </div>
      </div>
    </MainLayout>
  );
};

export default Dashboard;
