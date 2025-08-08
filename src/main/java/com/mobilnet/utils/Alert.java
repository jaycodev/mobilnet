package com.mobilnet.utils;

public class Alert {

	public static String sweetAlertInfo(String text) {
		return sweetAlert("Información", text, "info");
	}

	public static String sweetAlertSuccess(String text) {
		return sweetAlert("Éxito", text, "success");
	}

	public static String sweetAlertError(String text) {
		return sweetAlert("Error", text, "error");
	}

	public static String sweetAlert(String title, String msg, String icon) {
		title = escapeForJs(title);
		msg = escapeForJs(msg);

		String scriptText = """
				<script>
				    Swal.fire({
				        title: '%s',
				        html: '%s',
				        icon: '%s'
				    });
				</script>
				""";

		return String.format(scriptText, title, msg, icon);
	}

	public static String sweetImageUrl(String title, String text, String imageUrl) {
		title = escapeForJs(title);
		text = escapeForJs(text);

		String scriptText = """
				<script>
					Swal.fire({
						title: '%s',
						html: '%s',
						imageUrl: '%s',
						imageWidth: 400,
						imageHeight: 400,
						customClass: {
							image: 'rounded-circle' 
						}
					});
				</script>
				""";
		return String.format(scriptText, title, text, imageUrl);
	}

	public static String sweetAlertConfirm(String title, String text, String confirmButtonText, String cancelButtonText, String confirmUrl) {
		title = escapeForJs(title);
		text = escapeForJs(text);
		confirmButtonText = escapeForJs(confirmButtonText);
		cancelButtonText = escapeForJs(cancelButtonText);
		confirmUrl = escapeForJs(confirmUrl);

		String scriptText = """
			<script>
			    Swal.fire({
			        title: '%s',
			        html: '%s',
			        icon: 'warning',
			        showCancelButton: true,
			        confirmButtonColor: '#3085d6',
			        cancelButtonColor: '#d33',
			        confirmButtonText: '%s',
			        cancelButtonText: '%s'
			    }).then((result) => {
			        if (result.isConfirmed) {
			            window.location.href = '%s';
			        }
			    });
			</script>
		""";
		return String.format(scriptText, title, text, confirmButtonText, cancelButtonText, confirmUrl);
	}

	private static String escapeForJs(String input) {
		if (input == null) return "";
		return input.replace("\\", "\\\\").replace("'", "\\'");
	}
}