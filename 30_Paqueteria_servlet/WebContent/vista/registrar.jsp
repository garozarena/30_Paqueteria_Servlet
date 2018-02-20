<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Artículo</title>
</head>
<body>

	<h1>Registrar Artículo</h1>
	<form action="../adminArticulo?action=register" method="post">
		<table border="1" align="center">
			<tr>
				<td>Origen:</td>
				<td> <input id="origin-input" class="controls" size="40"
				type="text" placeholder="Introduce una localización de origen"	name="origen"> </td>
			</tr>
			<tr>
				<td>Destino:</td>
				<td><input id="destination-input" class="controls" type="text" size="40"
				placeholder="Introduce una localización de destino" name="destino"></td>
			</tr>
			<tr>
				<td>Paquete:</td>
				<td>Tamaño: <select name="paquete">
				<option value="pequeño">Pequeño</option>
				<option value="mediano">Mediano</option>
				<option value="grande">Grande</option>
			</select></td>
			</tr>
			<tr>
				<td>Fecha:</td>
				<td><input type="date" name="fecha" /></td>
			</tr>
			<tr>
				<td>Remitente:</td>
				<td><input type="text" name="remitente" /></td>
			</tr>
			<tr>
				<td>Transportista:</td>
				<td><input type="text" name="transportista" /></td>
			</tr>
			<tr>
				<td>Precio:</td>
				<td><input type="text" name="precio" /></td>
			</tr>

		</table>
		<br>
		<table border="0" align="center">
			<tr>
				<td><input type="submit" value="Agregar" name="agregar"></td>
			</tr>
	<div id="map"></div>
			</form>

        
 <script type="text/javascript">
		function initMap() {
			var map = new google.maps.Map(document.getElementById('map'), {
				mapTypeControl : false,
				center : {
					lat : 40.428462,
					lng : -3.704267
				},
				zoom : 13
			});
			new AutocompleteDirectionsHandler(map);
		}
		/**
		 * @constructor
		 */
		function AutocompleteDirectionsHandler(map) {
			this.map = map;
			this.originPlaceId = null;
			this.destinationPlaceId = null;
			this.travelMode = 'WALKING';
			var originInput = document.getElementById('origin-input');
			var destinationInput = document.getElementById('destination-input');
			var modeSelector = document.getElementById('mode-selector');
			this.directionsService = new google.maps.DirectionsService;
			this.directionsDisplay = new google.maps.DirectionsRenderer;
			this.directionsDisplay.setMap(map);
			var originAutocomplete = new google.maps.places.Autocomplete(
					originInput, {
						placeIdOnly : true
					});
			var destinationAutocomplete = new google.maps.places.Autocomplete(
					destinationInput, {
						placeIdOnly : true
					});
			this.setupClickListener('changemode-walking', 'WALKING');
			this.setupClickListener('changemode-transit', 'TRANSIT');
			this.setupClickListener('changemode-driving', 'DRIVING');
			this.setupPlaceChangedListener(originAutocomplete, 'ORIG');
			this.setupPlaceChangedListener(destinationAutocomplete, 'DEST');
			this.map.controls[google.maps.ControlPosition.TOP_LEFT]
					.push(originInput);
			this.map.controls[google.maps.ControlPosition.TOP_LEFT]
					.push(destinationInput);
			this.map.controls[google.maps.ControlPosition.TOP_LEFT]
					.push(modeSelector);
		}
		// Sets a listener on a radio button to change the filter type on Places
		// Autocomplete.
		AutocompleteDirectionsHandler.prototype.setupClickListener = function(
				id, mode) {
			var radioButton = document.getElementById(id);
			var me = this;
			radioButton.addEventListener('click', function() {
				me.travelMode = mode;
				me.route();
			});
		};
		AutocompleteDirectionsHandler.prototype.setupPlaceChangedListener = function(
				autocomplete, mode) {
			var me = this;
			autocomplete.bindTo('bounds', this.map);
			autocomplete
					.addListener(
							'place_changed',
							function() {
								var place = autocomplete.getPlace();
								if (!place.place_id) {
									window
											.alert("Por favor, seleccione una opción de la lista desplegable.");
									return;
								}
								if (mode === 'ORIG') {
									me.originPlaceId = place.place_id;
								} else {
									me.destinationPlaceId = place.place_id;
								}
								me.route();
							});
		};
		AutocompleteDirectionsHandler.prototype.route = function() {
			if (!this.originPlaceId || !this.destinationPlaceId) {
				return;
			}
			var me = this;
			this.directionsService.route({
				origin : {
					'placeId' : this.originPlaceId
				},
				destination : {
					'placeId' : this.destinationPlaceId
				},
				travelMode : this.travelMode
			}, function(response, status) {
				if (status === 'OK') {
					me.directionsDisplay.setDirections(response);
				} else {
					window.alert('Solicitud de dirección fallido debido a '
							+ status);
				}
			});
		};
	</script>

 <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB29iPuIgyP_AyzGThitYhQjiLmHARo5HQ&libraries=places&callback=initMap"
        async defer></script>
</body>
</html>