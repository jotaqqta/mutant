package com.dna.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.dna.app.controller.model.LoginUserDto;
import com.dna.app.controller.model.TokenDto;
import com.dna.app.security.jwtoken.ProviderToken;
import com.dna.app.util.ErrorMessage;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value = "/usuarios")
public class UserController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private ProviderToken providerToken;
	
    @PostMapping("/login")
    @ApiOperation(value = "Permite generar un token de autenticación.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Token generado."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 400, message = "Informacion del usuario incompleta o incorrecta.")
    })
    public ResponseEntity<TokenDto> login(
    	   @ApiParam(name="login", value="Credenciales de autenticación para obtener token.", required = true)
    	   @Valid @RequestBody LoginUserDto loginUsuario, BindingResult result){
        if(result.hasErrors()) {
			ErrorMessage errorMessage = new ErrorMessage(ErrorMessage.LOGIN);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.getMensaje(result));
        }
        UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(loginUsuario.getUserName(), loginUsuario.getPassword());
        Authentication authentication =authManager.authenticate(userAuth);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = providerToken.generarToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        TokenDto tokenDto = new TokenDto(token, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
    }

}
