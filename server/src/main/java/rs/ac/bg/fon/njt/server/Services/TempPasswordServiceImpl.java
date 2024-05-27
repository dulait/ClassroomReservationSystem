package rs.ac.bg.fon.njt.server.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Models.TempPassword;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Repositories.TempPasswordRepository;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TempPasswordServiceImpl implements TempPasswordService {

    private final TempPasswordRepository tempPasswordRepository;

    @Override
    public void saveTempPassword(User user, String tempPassword) {
        TempPassword tempPasswordEntity = new TempPassword();
        tempPasswordEntity.setPassword(tempPassword);
        tempPasswordEntity.setUser(user);
        tempPasswordEntity.setCreatedAt(new Date());
        tempPasswordEntity.setExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));

        tempPasswordRepository.save(tempPasswordEntity);
    }

    @Override
    public void invalidateTempPassword(User user) {
        tempPasswordRepository.deleteByUser(user);
    }

    @Override
    public boolean isTempPassword(User user, String password) {
        Optional<TempPassword> tempPasswordEntity = tempPasswordRepository.findByUser(user);
        if (tempPasswordEntity.isPresent()) {
            TempPassword tempPassword = tempPasswordEntity.get();
            return tempPassword.getPassword().equals(password) && tempPassword.getExpiresAt().after(new Date());
        }
        return false;
    }
}
