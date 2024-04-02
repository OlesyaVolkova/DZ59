/*1) Разработать приложение, в котором в 2 потока будут общаться друг с
другом 2 сущности. Диалог- по 5 реплик с каждой стороны.*/
public class Entity {
    private final String name;

    public Entity(String name) {
        this.name = name;
    }

    public synchronized void communicate(Entity otherEntity) {
        System.out.println(this.name + ": Добрый день, " + otherEntity.getName() + "!");
        System.out.println(otherEntity.getName() + ": Привет, " + this.name + "!");
        System.out.println(this.name + ": Как ваши дела?");
        System.out.println(otherEntity.getName() + ": Все хорошо, спасибо!");
        System.out.println(this.name + ": До свидания, " + otherEntity.getName() + "!");
        System.out.println(otherEntity.getName() + ": Пока, " + this.name + "!");
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Entity entity1 = new Entity("Entity1");
        Entity entity2 = new Entity("Entity2");

        Thread thread1 = new Thread(() -> {
            entity1.communicate(entity2);
        });

        Thread thread2 = new Thread(() -> {
            entity2.communicate(entity1);
        });

        thread1.start();
        thread2.start();
    }
}