import { API_DOMAIN, TicketEndpoints, UserEndpoints } from "@/enums/APIEnum";
import { Ticket } from "@/types/TicketType";
import axios from "axios";
import { FC, useEffect, useState } from "react";
import { ColumnDef } from "@tanstack/react-table";
import { TicketTable } from "./TicketTable";
import { DotsHorizontalIcon } from "@radix-ui/react-icons";
import { Button } from "../ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuPortal,
  DropdownMenuSub,
  DropdownMenuSubContent,
  DropdownMenuSubTrigger,
  DropdownMenuTrigger,
} from "../ui/dropdown-menu";
import { useAppSelector } from "@/hook/Redux";
import { User } from "@/types/userType";
import { getAllSupporters } from "@/api/user";
import { getAllTickets } from "@/api/ticket";
import { SearchTypeEnum } from "@/enums/SearchTypeEnum";
import { useRouter } from "next/router";

interface TicketListProps {
  searchType: SearchTypeEnum;
  searchValue: String;
}

const TicketList: FC<TicketListProps> = ({ searchType, searchValue }) => {
  // router
  const router = useRouter();

  // state
  const [tickets, setTickets] = useState<Ticket[]>([]);
  const [supporters, setSupporters] = useState<User[]>([]);
  const [searchResult, setSearchResult] = useState<Ticket[]>([]);

  // redux state
  const user: User = useAppSelector((state) => state.user.value as User);

  useEffect(() => {
    getAllTickets(localStorage.getItem("token") || "").then((res) => {
      setTickets(res);
      setSearchResult(res);
    });
    getAllSupporters(localStorage.getItem("token") || "").then((res) => {
      setSupporters(res);
    });
  }, []);

  // gı to ticket details
  const goToTicketDetails = (id: string) => {
    router.push(`/ticket/${id}`);
  };

  // delete ticket by id
  const deleteTicket = (id: string) => {
    axios
      .delete(TicketEndpoints.DeleteTicketById + id, {
        headers: {
          token: localStorage.getItem("token"),
        },
      })
      .then((res) => {
        if (res.data.success) {
          window.location.reload();
        }
      });
  };

  // assign ticket to supporter
  const assignSupport = (ticketId: number, assigneeId: number) => {
    axios
      .patch(
        API_DOMAIN + `/api/ticket/${ticketId}/assignee/${assigneeId}`,
        {},
        {
          headers: {
            token: localStorage.getItem("token"),
          },
        }
      )
      .then((res) => {
        if (res.data.success) {
          window.location.reload();
        }
      });
  };

  // delete assign
  const deleteAssign = (ticketId: number) => {
    axios
      .delete(API_DOMAIN + `/api/ticket/${ticketId}/assignee/`, {
        headers: {
          token: localStorage.getItem("token"),
        },
      })
      .then((res) => {
        if (res.data.success) {
          window.location.reload();
        }
      });
  };

  // search ticket
  useEffect(() => {
    const result = tickets.filter((ticket) => {
      switch (searchType) {
        case SearchTypeEnum.TITLE:
          return ticket.title.toLowerCase().includes(searchValue.toLowerCase());
        case SearchTypeEnum.DESCRIPTION:
          return ticket.description
            .toLowerCase()
            .includes(searchValue.toLowerCase());
        case SearchTypeEnum.STATUS:
          return ticket.status.status
            .toLowerCase()
            .includes(searchValue.toLowerCase());
        case SearchTypeEnum.PRIORITY:
          return ticket.priority.priority
            .toLowerCase()
            .includes(searchValue.toLowerCase());
        case SearchTypeEnum.ID:
          return ticket.id.toString().includes(searchValue.toString());
        case SearchTypeEnum.ASSIGNED_TO:
          return ticket.assignedTo?.email
            .toLowerCase()
            .includes(searchValue.toLowerCase());
        default:
          return tickets;
      }
    });
    setSearchResult(result);
  }, [searchValue, searchType]);

  // search ticket

  // this is the table columns
  const colums: ColumnDef<Ticket>[] = [
    {
      accessorKey: "id",
      header: "ID",
    },
    {
      accessorKey: "title",
      header: "Title",
    },
    {
      accessorKey: "description",
      header: "Description",
    },
    {
      accessorKey: "status",
      header: "Status",
    },
    {
      accessorKey: "priority",
      header: "Priority",
    },
    {
      accessorKey: "date",
      header: "created at",
    },
    {
      accessorKey: "assignedTo",
      header: "Assigned To",
    },
    {
      header: "Actions",
      cell: (row) => (
        <div className="flex flex-row space-x-2">
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button variant="ghost">
                <DotsHorizontalIcon />
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent className="w-56">
              <DropdownMenuGroup>
                {/* TODO: add Shortcut  */}
                <DropdownMenuItem
                  onClick={() => goToTicketDetails(row.row.getValue("id"))}>
                  Details
                </DropdownMenuItem>
                {user.role === "ADMIN" && (
                  <DropdownMenuSub>
                    <DropdownMenuSubTrigger>Assign</DropdownMenuSubTrigger>
                    <DropdownMenuPortal>
                      <DropdownMenuSubContent>
                        {supporters &&
                          supporters.map((supporter) => {
                            return (
                              <DropdownMenuItem
                                key={supporter.id}
                                onClick={() =>
                                  assignSupport(
                                    row.row.getValue("id"),
                                    supporter.id
                                  )
                                }>
                                {supporter.title.title} | {supporter.email}
                              </DropdownMenuItem>
                            );
                          })}
                      </DropdownMenuSubContent>
                    </DropdownMenuPortal>
                  </DropdownMenuSub>
                )}
                {user.role === "ADMIN" && (
                  <DropdownMenuItem
                    onClick={() => deleteAssign(row.row.getValue("id"))}>
                    Remove Assign
                  </DropdownMenuItem>
                )}
                {user.role === "ADMIN" && (
                  <DropdownMenuItem
                    onClick={() => deleteTicket(row.row.getValue("id"))}>
                    Delete
                  </DropdownMenuItem>
                )}
                {(user && user.role === "ADMIN") || user.role === "SUPPORT" ? (
                  // TODO: add close ticket
                  <DropdownMenuItem>Close</DropdownMenuItem>
                ) : null}
              </DropdownMenuGroup>
            </DropdownMenuContent>
          </DropdownMenu>
        </div>
      ),
    },
  ];

  return (
    <div>
      <TicketTable columns={colums} data={searchResult} />
    </div>
  );
};

export default TicketList;
