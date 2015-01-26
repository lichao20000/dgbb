package com.dglt.comm.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Generics��util��.
 */
@SuppressWarnings("unchecked")
public class GenericsUtils {
        private static final Log log = LogFactory.getLog(GenericsUtils.class);

        private GenericsUtils() {
        }

        /**
         * ͨ������,��ö���Classʱ�����ĸ���ķ��Ͳ���������. ��public BookManager extends GenricManager<Book>
         *
         * @param clazz The class to introspect
         * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
         */
        public static Class getSuperClassGenricType(Class clazz) {
                return getSuperClassGenricType(clazz, 0);
        }

        /**
         * ͨ������,��ö���Classʱ�����ĸ���ķ��Ͳ���������. ��public BookManager extends GenricManager<Book>
         *
         * @param clazz clazz The class to introspect
         * @param index the Index of the generic declaration,start from 0.
         * @return the index generic declaration, or <code>Object.class</code> if cannot be determined
         */
        public static Class getSuperClassGenricType(Class clazz, int index) {

                Type genType = clazz.getGenericSuperclass();    //��ȡclazz��ֱ�ӳ���
                /*�ж��Ƿ�̳��Դ���*/
                if (!(genType instanceof ParameterizedType)) {
                        log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
                        return Object.class;
                }

                Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                /*�жϴ��Ĳ�����index �Ƿ�Ϸ�*/
                if (index >= params.length || index < 0) {
                        log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                                        + params.length);
                        return Object.class;
                }
                /*�ж��Ƿ������������ͣ���Ϊ������ �������� T */
                if (!(params[index] instanceof Class)) {
                        log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
                        return Object.class;
                }
                return (Class) params[index];
        }
}
