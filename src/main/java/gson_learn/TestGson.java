package gson_learn;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by useheart on 2020/6/22
 *
 * @author useheart
 */
public class TestGson {
    @Test
    public void toJsonList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<List<Integer>> twoList = new ArrayList<>();
        twoList.add(list);
        List<List<List<Integer>>> threeList = new ArrayList<>();
        threeList.add(twoList);

        Gson gson = new Gson();
        System.out.println(gson.toJson(threeList));
        Object object = gson.fromJson("[[[1]]]", new TypeToken<List<List<List<Integer>>>>() {
        }.getType());
        System.out.println(object);
    }

    @Test
    public void testLearnJar() {
        TypeToken<List<Integer>> listTypeToken = new TypeToken<List<Integer>>() {
        };
        System.out.println("listTypeToken-Type:" + listTypeToken.getType());
        System.out.println("listTypeToken-RawType:" + listTypeToken.getRawType());
        System.out.println("listTypeToken-Class:" + listTypeToken.getClass());
        System.out.println();

        TypeToken<List<?>> wildcardTypeListTypeToken = new TypeToken<List<?>>() {
        };
        System.out.println("wildcardTypeListTypeToken-Type:" + wildcardTypeListTypeToken.getType());
        System.out.println("wildcardTypeListTypeToken-RawType:" + wildcardTypeListTypeToken.getRawType());
        System.out.println("wildcardTypeListTypeToken-Class:" + wildcardTypeListTypeToken.getClass());
        System.out.println();

        TypeToken<String> stringTypeToken = new TypeToken<String>() {
        };
        System.out.println("stringTypeToken-Type:" + stringTypeToken.getType());
        System.out.println("stringTypeToken-RawType:" + stringTypeToken.getRawType());
        System.out.println("stringTypeToken-Class:" + stringTypeToken.getClass());
        System.out.println();

        TypeToken<List<List<Integer>>> doubleListTypeToken = new TypeToken<List<List<Integer>>>() {
        };
        System.out.println("doubleListTypeToken-Type:" + doubleListTypeToken.getType());
        System.out.println("doubleListTypeToken-RawType:" + doubleListTypeToken.getRawType());
        System.out.println("doubleListTypeToken-Class:" + doubleListTypeToken.getClass());
        System.out.println();
    }

    @Test
    public void learnClass() {
        Class<String> stringClazz = String.class;
        System.out.println(stringClazz.getSimpleName());
        System.out.println(stringClazz.getClass());
        System.out.println(stringClazz.getClassLoader());
        System.out.println(stringClazz.getComponentType());
        System.out.println(stringClazz.getSuperclass());
        System.out.println(stringClazz.getTypeName());
        System.out.println(stringClazz.getTypeParameters());
        System.out.println(stringClazz.getGenericSuperclass());

        ArrayList<Integer> arrayList = new ArrayList<>();
        Type genericSuperclass = arrayList.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass);
        System.out.println(arrayList.getClass().getGenericInterfaces()[0]);
        System.out.println(arrayList.getClass().getInterfaces()[0]);

        System.out.println(new Object().getClass().getGenericSuperclass());
    }

    @Test
    public void learnType() {
        String[] stringArray = new String[]{};
        System.out.println(stringArray.getClass().getComponentType());
        //System.out.println(((GenericArrayType)stringArray.getClass()).getGenericComponentType());
    }
}
