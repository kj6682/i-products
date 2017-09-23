package org.kj6682.i.products;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Function<T, R> myFunction = new Function<T, R>() {
 * public R apply(T t) {
 * return null;
 * }
 * };
 */
public class NameAttributeTest {
    Function<Object, Application.Name> toName = new Function<Object, Application.Name>() {
        public Application.Name apply(Object t) {
            return new Application.Name(t.toString());
        }
    };

    @Test
    public void convertSingleObject() {

        Object o = "Baba";

        Application.Name n = toName.apply(o);
        Assert.assertEquals(o.toString(), n.getName());

    }

    @Test
    public void convertListOfObjects() {

        Object o1 = "Baba";
        Object o2 = "Baba whisky";
        Object o3 = "Baba limoncello";

        List<Object> objects = new LinkedList<>();
        objects.add(o1);
        objects.add(o2);
        objects.add(o3);

        List<Application.Name> names = objects.stream()
                .map(toName)
                .collect(Collectors.<Application.Name>toList());

        for (int i = 0 ; i < names.size(); i++) {
            Assert.assertEquals(objects.get(i).toString(), names.get(i).getName());
        }
    }


}//:)
