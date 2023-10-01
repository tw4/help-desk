import { RootState } from "@/store/store";
import { User } from "@/types/userType";
import { useAppSelector } from "@/hook/Redux";
import {
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuShortcut,
} from "@/components/ui/dropdown-menu";
import {
  DropdownMenu,
  DropdownMenuTrigger,
  DropdownMenuLabel,
  DropdownMenuSeparator,
} from "@/components/ui/dropdown-menu";
import { Button } from "@/components/ui/button";
import { removeUser } from "@/store/features/user/userSlice";
import { useDispatch } from "react-redux";

const Navbar = () => {
  // Redux State
  const user: User = useAppSelector(
    (state: RootState) => state.user.value as User
  );
  const dispatch = useDispatch();

  // user logout
  const logout = () => {
    dispatch(removeUser());
    localStorage.removeItem("token");
    window.location.href = "/";
  };

  return (
    <nav className="h-12 border-b-[1px] border-gray-300 flex flex-row items-center justify-between">
      <span className="ml-4 font-bold">HELP DESK</span>
      <div>
        {/* TODO: Add Shortcut functions */}
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="outline">
              <div className="w-5 h-5 rounded-full bg-gradient-to-r from-green-300 via-blue-500 to-purple-600"></div>
              <div className="ml-2">
                {user?.name} {user?.surname}
              </div>
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent className="w-56">
            <DropdownMenuLabel>title: {user?.title.title}</DropdownMenuLabel>
            <DropdownMenuSeparator />
            <DropdownMenuGroup>
              <DropdownMenuItem>
                My Profile
                <DropdownMenuShortcut>⌘P</DropdownMenuShortcut>
              </DropdownMenuItem>
              <DropdownMenuItem>
                Account Settings
                <DropdownMenuShortcut>⌘A</DropdownMenuShortcut>
              </DropdownMenuItem>
              <DropdownMenuItem onClick={logout}>
                Log Out
                <DropdownMenuShortcut>⌘L</DropdownMenuShortcut>
              </DropdownMenuItem>
            </DropdownMenuGroup>
          </DropdownMenuContent>
        </DropdownMenu>
      </div>
    </nav>
  );
};

export default Navbar;
