# Vexeexpress-server

## Hướng dẫn chạy script docker

**Lưu ý**: 1 số option liên quan như database, dev thì sẽ không thể chạy được

### Windows

#### Cho phép chạy powershell script

Trước khi có thể chắc chắn chạy được powershell script hãy mởi Powershell với
quyền admin và chạy 1 trong 2 lệnh sau

```
Set-ExecutionPolicy RemoteSigned
```

hoặc

```
Set-ExecutionPolicy Unrestricted
```

Sau khi thực hiện không cần phải chạy thêm lần nữa nhưng hãy lưu ý nếu muốn hệ
thống bảo mật hơn sau này và không còn tiếp tục dự án có thể chạy lệnh trên
Powershell với quyền admin để ngăn chặn việc chạy powerscript bừa bãi

```
Set-ExecutionPolicy Restricted
```

#### Sử dụng script

Chạy script thông thường để ra những option bạn muốn chạy

```
.\script.ps1
```

Đây là câu lệnh đơn giản nếu muốn khởi động container nhanh chóng

```
.\script.ps1 rebuild-all-cache
```

Thông thường nếu chỉ muốn xóa container và image trước rồi tự build image và
start container lại có thể sử dụng lệnh này, dù các container, image trước
có hay không vẫn không quan trọng nó sẽ vẫn chạy bình thường.

**Lưu ý**: Nếu sau này không còn bất cứ dự án nào dùng docker nữa hãy là sạch
với `docker system prune -a` sẽ xóa toàn bộ docker image và container hiện có
để làm sạch hệ thống và giảm bớt bộ nhớ.

### Linux

Linux yêu cầu bảo mật thấp hơn nên chỉ dơn giản dùng

```
sh script.sh rebuild-all-cache
```

hay

```
bash script.sh rebuild-all-cache
```