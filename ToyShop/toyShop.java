package ToyShop;
import java.io.*;
import java.util.*;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private double dropFrequency;

    public Toy(int id, String name, int quantity, double dropFrequency) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.dropFrequency = dropFrequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDropFrequency() {
        return dropFrequency;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Название: " + name + ", Количество: " + quantity + ", Частота выпадения: " + dropFrequency;
    }
}

class ToyStore {
    private List<Toy> toys;

    public ToyStore() {
        toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public Toy getRandomToy() {
        if (toys.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(toys.size());
        return toys.get(randomIndex);
    }

    public void saveToysToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Toy toy : toys) {
                writer.println(toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getDropFrequency());
            }
            System.out.println("Сохранено в файл: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadToysFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double dropFrequency = Double.parseDouble(parts[3]);
                    addToy(new Toy(id, name, quantity, dropFrequency));
                }
            }
            System.out.println("Загружено из файла: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Магазин игрушек: " + toys;
    }
}

public class toyShop {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.addToy(new Toy(1, "Мяч", 10, 0.2));
        toyStore.addToy(new Toy(2, "Кукла", 15, 0.1));
        toyStore.addToy(new Toy(3, "Пазл", 20, 0.3));

        System.out.println("Игрушки в магазине:");
        System.out.println(toyStore);

        toyStore.saveToysToFile("toys.txt");

        ToyStore loadedToyStore = new ToyStore();
        loadedToyStore.loadToysFromFile("toys.txt");

        System.out.println("\nИгрушки в магазине после загрузки:");
        System.out.println(loadedToyStore);

        Toy randomToy = loadedToyStore.getRandomToy();
        if (randomToy != null) {
            System.out.println("\nСлучайно выбранная игрушка:");
            System.out.println(randomToy);
        } else {
            System.out.println("\nВ магазине нет игрушек.");
        }
    }
}

