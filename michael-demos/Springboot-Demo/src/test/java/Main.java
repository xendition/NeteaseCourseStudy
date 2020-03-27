import org.junit.Assert;
import org.junit.Test;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/11/26 15:30
 */
public class Main {

    @Test
    public void t(){
        final BeanCopier beanCopier = BeanCopier.create(User.class, UserDTO.class, false);
        User user = new User();
        user.setAge(10);
        user.setName("zhangsan");
        user.setDate(new Date());
        UserDTO userDto = new UserDTO();
        beanCopier.copy(user, userDto, null);
        Assert.assertEquals(10, userDto.getAge());
        Assert.assertEquals("zhangsan", userDto.getName());
    }
}
