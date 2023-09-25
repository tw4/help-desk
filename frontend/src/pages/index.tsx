import { Button } from "@/components/ui/button";
import { Form } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useForm } from "react-hook-form";

const Home = () => {
  const form = useForm();
  return (
    <main className="max-w[1440px] h-[100vh]  flex flex-row items-center justify-center">
      <div>
        <Form {...form}>
          <form>
            <Label>Email</Label>
            <Input type="email" placeholder="Email" />
            <Label>Password</Label>
            <Input type="password" placeholder="Password" />
            <Button className="mt-5">Sign In</Button>
          </form>
        </Form>
      </div>
    </main>
  );
};

export default Home;
