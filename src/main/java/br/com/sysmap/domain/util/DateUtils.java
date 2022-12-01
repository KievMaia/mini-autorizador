package br.com.sysmap.domain.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static final String TIMESTAMP_FORMAT = "dd-MM-yyyy HH:mm:ss";

	/**
	 * Converte {@link LocalDateTime} para {@link String}
	 *
	 * @param data {@link LocalDateTime}: a data a ser convertida em {@link String}
	 * @return {@link String}
	 */
	public static String convertLocalDateTimeToSring(final LocalDateTime data) {
		try {
			return data.format(DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT));
		} catch (final Exception e) {
			return null;
		}
	}
}
