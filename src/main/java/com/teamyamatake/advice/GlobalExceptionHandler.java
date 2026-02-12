package com.teamyamatake.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatch(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "不正なパラメータです");
        return "redirect:/";
    }
}
