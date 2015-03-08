# FlightStatus
Flight Status Application

Functionality - <br>
	The flight has a default schedule of 18:30. The Change Time Button can be used to test the application via 	the 	TimePicker. The following are the behavioral traits - <br>
•	Default - Flight is on Time <br>
•	Selection from 00:00 to 16:29 - On Time <br>
•	Selection from 17:30 to 18:10 - Boarding <br>
•	Selection from 18:11 to 18:30 - Gate Closed <br>
•	Selection from 16:30 to 17:29 - Flight is delayed to 19:30 (this status change is not reverted by selecting a lesser value of time). Now the previous set of status changes can be checked with 1 hour advance <br>
•	Selection from 00:00 to 17:29 (After status = Delayed) - On Time <br>
•	Selection from 18:30 to 19:10 (After status = Delayed) - Boarding <br>
•	Selection from 19:11 to 19:30 (After status = Delayed) - Gate Closed <br>

The Cancel Flight Button can be pressed at any time to set the status to Canceled. This makes the time picker invisible and changes the Cancel Flight button label to Reset Flight. Reset Flight button can be pressed to reset the application to its default state.
