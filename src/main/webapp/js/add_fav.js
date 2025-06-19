function attachFavouriteEvents() {
        document.querySelectorAll(".add-fav").forEach(btn => {
            btn.addEventListener("click", function (e) {
                e.preventDefault();
                const productId = this.dataset.productid;

                fetch("add-favourite", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: new URLSearchParams({
                            productID: productId
                          })
                })
                .then(res => res.text())
                .then(data => {
                    if (data === "added") {
                        alert("Đã thêm vào danh sách yêu thích!");
                    } else if (data === "exists") {
                        alert("Sản phẩm đã có trong danh sách yêu thích!");
                    } else {
                        alert("Có lỗi xảy ra khi thêm yêu thích.");
                    }
                })
                .catch(err => {
                    console.error("Lỗi khi thêm yêu thích:", err);
                });
            });
        });
    }
attachFavouriteEvents()