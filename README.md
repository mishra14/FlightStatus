# FlightStatus
Flight Status Application

Functionality - 
	The flight has a default schedule of 18:30. The Change Time Button can be used to test the application via 	the 	TimePicker. The following are the behavioral traits - 
•	Default - Flight is on Time
•	Selection from 00:00 to 16:29 - On Time
•	Selection from 17:30 to 18:10 - Boarding
•	Selection from 18:11 to 18:30 - Gate Closed
•	Selection from 16:30 to 17:29 - Flight is delayed to 19:30 (this status change is not reverted by selecting a lesser value of time). Now the previous set of status changes can be checked with 1 hour advance
•	Selection from 00:00 to 17:29 (After status = Delayed) - On Time
•	Selection from 18:30 to 19:10 (After status = Delayed) - Boarding
•	Selection from 19:11 to 19:30 (After status = Delayed) - Gate Closed

The Cancel Flight Button can be pressed at any time to set the status to Canceled. This makes the time picker invisible and changes the Cancel Flight button label to Reset Flight. Reset Flight button can be pressed to reset the application to its default state.
