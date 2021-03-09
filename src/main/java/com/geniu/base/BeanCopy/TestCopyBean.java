package com.geniu.base.BeanCopy;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @Author: zhongshibo
 * @Date: 2021/3/9 19:04
 */
public class TestCopyBean {

    public static void main(String[] args)
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        PersonDO personDO = new PersonDO();
        personDO.setName("Hollis");
        personDO.setAge(26);
        personDO.setBirthday(new Date());
        personDO.setId(1);
        TestCopyBean mapperTest = new TestCopyBean();
        mapperTest.mappingBySpringBeanUtils(personDO, 100);
        mapperTest.mappingBySpringBeanUtils(personDO, 1000);
        mapperTest.mappingBySpringBeanUtils(personDO, 10000);
        mapperTest.mappingBySpringBeanUtils(personDO, 100000);
        mapperTest.mappingBySpringBeanUtils(personDO, 1000000);
        mapperTest.mappingBySpringBeanUtils(personDO, 10000000);
        mapperTest.mappingByCglibBeanCopier(personDO, 100);
        mapperTest.mappingByCglibBeanCopier(personDO, 1000);
        mapperTest.mappingByCglibBeanCopier(personDO, 10000);
        mapperTest.mappingByCglibBeanCopier(personDO, 100000);
        mapperTest.mappingByCglibBeanCopier(personDO, 1000000);
        mapperTest.mappingByCglibBeanCopier(personDO, 10000000);
        mapperTest.mappingByApachePropertyUtils(personDO, 100);
        mapperTest.mappingByApachePropertyUtils(personDO, 1000);
        mapperTest.mappingByApachePropertyUtils(personDO, 10000);
        mapperTest.mappingByApachePropertyUtils(personDO, 100000);
        mapperTest.mappingByApachePropertyUtils(personDO, 1000000);
        mapperTest.mappingByApacheBeanUtils(personDO, 100);
        mapperTest.mappingByApacheBeanUtils(personDO, 1000);
        mapperTest.mappingByApacheBeanUtils(personDO, 10000);
        mapperTest.mappingByApacheBeanUtils(personDO, 100000);
        mapperTest.mappingByApacheBeanUtils(personDO, 1000000);
        mapperTest.mappingByApacheBeanUtils(personDO, 10000000);
    }

    private void mappingBySpringBeanUtils(PersonDO personDO, int times) {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            org.springframework.beans.BeanUtils.copyProperties(personDO, personDTO);
        }
        stopwatch.stop();
        System.out.println("mappingBySpringBeanUtils cost :" + stopwatch.
                getTotalTimeMillis());
    }

    private void mappingByCglibBeanCopier(PersonDO personDO, int times) {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            BeanCopier copier = BeanCopier.create(PersonDO.class, PersonDTO.
                    class, false);
            copier.copy(personDO, personDTO, null);
        }
        stopwatch.stop();
        System.out.println("mappingByCglibBeanCopier cost :" + stopwatch.
                getTotalTimeMillis());
    }


    private void mappingByApacheBeanUtils(PersonDO personDO, int times)
            throws InvocationTargetException, IllegalAccessException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            BeanUtils.copyProperties(personDTO, personDO);
        }
        stopwatch.stop();
        System.out.println("mappingByApacheBeanUtils cost :" + stopwatch.
                getTotalTimeMillis());
    }

    private void mappingByApachePropertyUtils(PersonDO personDO, int times)
            throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        for (int i = 0; i < times; i++) {
            PersonDTO personDTO = new PersonDTO();
            PropertyUtils.copyProperties(personDTO, personDO);
        }
        stopwatch.stop();
        System.out.println("mappingByApachePropertyUtils cost :" + stopwatch.
                getTotalTimeMillis());
    }
}