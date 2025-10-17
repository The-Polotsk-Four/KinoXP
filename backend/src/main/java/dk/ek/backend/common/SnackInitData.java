package dk.ek.backend.common;

import dk.ek.backend.catalog.model.Snack;
import dk.ek.backend.catalog.repository.SnackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SnackInitData implements CommandLineRunner {

    private final SnackRepository snackRepository;

    public SnackInitData(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (snackRepository.count() > 0) {
            return;
        }
        List<Snack> snacks = Arrays.asList(
                lavSnack("Kims Chips", 25, 50),
                lavSnack("Haribo Matador Mix", 30, 40),
                lavSnack("Skildpadder", 15, 60),
                lavSnack("Marabou Mælkechokolade", 35, 30),
                lavSnack("Toms Guld Barre", 10, 100),
                lavSnack("Popcorn", 20, 80),
                lavSnack("Coca-Cola", 25, 70),
                lavSnack("Faxe Kondi", 25, 60),
                lavSnack("Kildevand", 15, 100),
                lavSnack("Ben & Jerry’s Is", 45, 20)
        );

        snackRepository.saveAll(snacks);
    }

    private Snack lavSnack(String navn, int pris, int antal) {
        Snack snack = new Snack();
        snack.setName(navn);
        snack.setPrice(pris);
        snack.setQuantity(antal);
        return snack;
    }
}
