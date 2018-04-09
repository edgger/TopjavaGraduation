package guru.optimal.topjava.graduation.util;


import guru.optimal.topjava.graduation.model.AbstractBaseEntity;
import javassist.NotFoundException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) throws NotFoundException {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) throws NotFoundException {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) throws NotFoundException {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) throws NotFoundException {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}