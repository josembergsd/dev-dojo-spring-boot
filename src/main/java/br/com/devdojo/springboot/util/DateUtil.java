package br.com.devdojo.springboot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
 
/**
 * 
 * @author Josemberg Sousa Duarte
 *
 */
@Component
public class DateUtil {
	public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(localDateTime); 
	}
}
