<h1><b>COCOMO</b></h1>
<form name = "getSize" action = "calculateCocomo" method = "Post">
	<table>
		<tr>
			<td> Enter KLOC: </td>
			<td><input id = "klocSize"input type = "text" name="size"/></td>
		</tr>

		<th><input id  = "buttonSubmit" input type="submit" value = "Submit" name = "find"/></th>
		<th><input type="reset" value = "Reset" name = "reset"/></th>
	</table>
</form>


	<table name = "calculated" >
		<tr>
			<td name = "mode"><b>${mode} </b></td>
		</tr>
		<tr>
			<td name = "effort"><b>${effort} </b></td>
		</tr>
		<tr>
			<td name = "time"><b>${time}</b></td>
		</tr>
	</table>














