const token = "80117275-f321-11ee-8bfa-8a2dda8ec551";

// Hàm gọi API lấy danh sách tỉnh
function fetchProvinces() {
    fetch('https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province', {
        headers: {
            'Token': token
        }
    })
        .then(response => response.json())
        .then(data => {
            const provinceSelect = document.getElementById('province');
            data.data.forEach(province => {
                const option = document.createElement('option');
                option.value = province.ProvinceID;
                option.textContent = province.ProvinceName;
                provinceSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching provinces:', error));
}

// Hàm gọi API lấy danh sách huyện theo tỉnh
function fetchDistricts(provinceId) {
    fetch(`https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=${provinceId}`, {
        headers: {
            'Token': token
        }
    })
        .then(response => response.json())
        .then(data => {
            const districtSelect = document.getElementById('district');
            districtSelect.innerHTML = '<option value="">Chọn Quận/Huyện</option>';
            data.data.forEach(district => {
                const option = document.createElement('option');
                option.value = district.DistrictID;
                option.textContent = district.DistrictName;
                districtSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching districts:', error));
}

// Hàm gọi API lấy danh sách xã theo huyện
function fetchWards(districtId) {
    fetch(`https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=${districtId}`, {
        headers: {
            'Token': token
        }
    })
        .then(response => response.json())
        .then(data => {
            const wardSelect = document.getElementById('ward');
            wardSelect.innerHTML = '<option value="">Chọn Phường/Xã</option>';
            data.data.forEach(ward => {
                const option = document.createElement('option');
                option.value = ward.WardCode;
                option.textContent = ward.WardName;
                wardSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching wards:', error));
}

document.addEventListener('DOMContentLoaded', () => {
    fetchProvinces();

    document.getElementById('province').addEventListener('change', (event) => {
        const provinceId = event.target.value;
        if (provinceId) {
            fetchDistricts(provinceId);
        } else {
            document.getElementById('district').innerHTML = '<option value="">Chọn Quận/Huyện</option>';
            document.getElementById('ward').innerHTML = '<option value="">Chọn Phường/Xã</option>';
        }
    });

    document.getElementById('district').addEventListener('change', (event) => {
        const districtId = event.target.value;
        if (districtId) {
            fetchWards(districtId);
        } else {
            document.getElementById('ward').innerHTML = '<option value="">Chọn Phường/Xã</option>';
        }
    });
});