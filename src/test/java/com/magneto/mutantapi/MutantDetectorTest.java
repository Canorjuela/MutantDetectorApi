package com.magneto.mutantapi;

import com.magneto.mutantapi.service.MutantDetector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MutantDetectorTest {

	MutantDetector detector = new MutantDetector();

	@Test
	void testEsMutante() {
		String[] adn = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		assertTrue(detector.isMutant(adn), "Debe detectar como mutante");
	}

	@Test
	void testNoEsMutante() {
		String[] adn = {
				"ATGCGA",
				"CAGTAC",
				"TTACTG",
				"AGACGG",
				"CTCCTA",
				"TCACTG"
		};
		assertFalse(detector.isMutant(adn), "Debe detectar como humano (no mutante)");
	}

	@Test
	void testSecuenciaInvalida() {
		String[] adn = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAXG", "CCCCTA", "TCACTG"};
		assertThrows(IllegalArgumentException.class, () -> detector.isMutant(adn));
	}
}
