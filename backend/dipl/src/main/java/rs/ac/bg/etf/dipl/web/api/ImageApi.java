package rs.ac.bg.etf.dipl.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rs.ac.bg.etf.dipl.web.model.image.ImageResponse;

@Api(value = "Image API", description = "WEB API for saving movies")
@RequestMapping("v1/image")
public interface ImageApi {

	@PostMapping("/upload")
	@ApiOperation("Get movies")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Image successfully uploaded"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 500, message = "Internal server error"),
	})
    public ResponseEntity<ImageResponse> upload(@RequestParam(value = "image") MultipartFile image);
}
