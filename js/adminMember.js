var orderColumn = [
  "memberNum",
  "memberId",
  "memberName",
  "memberBirth",
  "memberGender",
  "memberEmail",
  "memberPhone",
  "memberJoinDate",
  "memberReportNum",
  "memberReport",
];

$("table.adminMemberTbl").DataTable();
/*
$("table.adminMemberTbl").DataTable({
  paging: true,
  searching: false,
  info: true,
  autoWidth: false,
  responsive: true,
  lengthChange: true,
  lengthMenu: [10, 20, 50],
  ordeing: true,
  columns: [
    {
      data: "memberNum",
    },
    {
      data: "memberId",
    },
    {
      data: "memberName",
    },
    {
      data: "memberBirth",
    },
    {
      data: "memberGender",
    },
    {
      data: "memberEmail",
    },
    {
      data: "memberPhone",
    },
    {
      data: "memberJoinDate",
    },
    {
      data: "memberReportNum",
    },
    {
      data: "memberReport",
    },
  ],

  processing: true,
  serverSide: true,
});
*/
